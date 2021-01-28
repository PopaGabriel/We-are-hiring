package Apllication.Interfaces;

import Apllication.Notifi;
import Apllication.User;

public interface Subject {
    void addObserver(User user);
    void removeObserver(User user);
    void notifyAllObservers(Notifi notifi);
}
