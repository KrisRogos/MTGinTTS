package Core;

import jdk.nashorn.internal.runtime.ECMAException;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeckLoaderThread implements Runnable {

    public enum LoaderState {
        NOT_READY,
        SETTING_UP,
        IDLE,
        WORKING
    }

    public class DeckLoaderException extends Exception {

    }

    private Thread t;
    private boolean m_Alive;
    private String m_DeckFile;
    private String m_OutDir;
    private LoaderState m_State;
    public Lock m_StateLock;
    private Deck m_Task;
    private boolean m_IsComplete;

    public LoaderState getState() {
        return m_State;
    }

    public DeckLoaderThread(String a_DeckFile, String a_OutputDir) {
        m_Alive = false;
        m_StateLock = new ReentrantLock();
        m_StateLock.lock();
        try {
            m_State = LoaderState.NOT_READY;
        }
        finally {
            m_StateLock.unlock();
        }
        m_IsComplete = true;

        m_DeckFile = a_DeckFile;
        m_OutDir = a_OutputDir;

        m_Alive = true;
    }

    @Override
    public void run() {
        while (m_Alive) {
            try {
                if(m_StateLock.tryLock()) {
                    try {
                        switch (m_State) {
                            case NOT_READY:
                            case SETTING_UP:
                                Thread.sleep(50);
                                break;
                            case IDLE:
                                if (!m_IsComplete && m_DeckFile != null && m_DeckFile != "")
                                {
                                    m_State = LoaderState.WORKING;
                                }
                                break;
                            case WORKING:
                                m_Task = new Deck(m_DeckFile, m_OutDir);
                                m_IsComplete = true;
                                m_State = LoaderState.IDLE;
                                break;
                        }
                    }
                    finally {
                        m_StateLock.unlock();
                    }

                }
                Thread.sleep(50);
            }
            catch (Exception e) {
                m_Alive = false;
                e.printStackTrace();
            }
        }
    }
/*
    public void start() {
        m_IsComplete = false;
        m_State = LoaderState.IDLE;

        if (t == null) {
            System.out.println("Starting the loader" + m_State + m_IsComplete);
            t = new Thread(this);
            t.start();
        }
    }

    public boolean IsTaskComplete() {
        return m_IsComplete;
    }

    public Deck GetDeck () throws DeckLoaderException {
        if (IsTaskComplete()) {
            return m_Task;
        }
        else {
            throw new DeckLoaderException();
        }
    }*/


}
