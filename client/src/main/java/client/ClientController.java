package client;

import com.ubb.cms.Conference;
import com.ubb.cms.Edition;
import com.ubb.cms.Paper;
import com.ubb.cms.User;
import service.common.IConferenceClient;
import service.common.IConferenceServer;
import service.common.Observer;
import service.exception.ServiceException;
import utils.DateUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Raul on 26/04/2017.
 */
public class ClientController extends UnicastRemoteObject implements IConferenceClient {
    private IConferenceServer server;
    private List<Observer<?>> observers = new ArrayList<>();

    public ClientController(IConferenceServer server) throws RemoteException {
        this.server = server;


    }

    public void addUser(User user) throws ServiceException {
        server.addUser(user);
    }


    public List<User> getAllUsers() {
        return server.getAllUser();
    }

    public User getUserById(int userId) {
        return server.getUserById(userId);
    }


    public Edition getEditionById(int editionId) {
        return server.getEditionById(editionId);
    }

    public void addPaper(Paper paper) throws ServiceException {
        server.addPaper(paper);
    }


    public List<Conference> getAllConferences() {
        return server.getAllConferences();
    }

    public List<Edition> getAllEdition() {
        return server.getAllEditions();
    }


    public List<Paper> getAllPapers() {
        return server.getAllPapers();
    }


    public void updateUser(User newUser) throws ServiceException {
        server.updateUser(newUser);
    }

    public List<Edition> getEditionAfterDate(Date date)
    {
        return server.getEditionAfterDate(date);
    }


    public User login(String username, String password) throws ServiceException {
        User user = new User(username, password);
        return server.login(user, this);
        /*try {

        }
        catch (ServiceException exception)
        {
            logger.info(exception.getStackTrace());
        }*/

        //user = userL;
    }

    public void logout(String username) throws ServiceException {
        try {
            server.logout(username);
        } catch (ServiceException ex) {
            throw new ServiceException("Err");
        }
    }


    @Override
    public void showUpdated() throws RemoteException {
        //logger.info(updateType);
        this.notifyObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {

    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addConference(String conferenceName) throws ServiceException {
        server.addConference(new Conference(conferenceName));
    }

    public void addEdition(Conference conference, LocalDate beginningDate, LocalDate endingDate, String name, LocalDate paperSubmissionDeadline, LocalDate finalDeadline) throws ServiceException {
        Edition edition = new Edition(
                conference,
                DateUtils.asDate(beginningDate),
                DateUtils.asDate(endingDate),
                name,
                DateUtils.asDate(paperSubmissionDeadline),
                DateUtils.asDate(finalDeadline)
        );

        server.addEdition(edition);
    }
}
