package ua.nure.coworkinghelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.nure.coworkinghelper.model.*;
import ua.nure.coworkinghelper.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/api")
public class Controller {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    CoWorkingRepository coWorkingRepository;

    @Autowired
    RoomRepository roomRepository;


    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> findCustomers() {
        return new ArrayList<>(customerRepository.findAll());
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer findCustomer(@PathVariable("id") Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer createCustomer(@RequestBody Customer customer) {
        try {
            return customerRepository.save(new Customer(customer.getName(), customer.getPassword(), customer.getPhone(),
                    customer.getAdmin(), customer.getEmail()));
        } catch (Exception e) {
            return customer;
        }
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Optional<Customer> customerData = customerRepository.findById(id);
        if (customerData.isPresent()) {
            Customer _customer = customerData.get();
            _customer.setName(customer.getName());
            _customer.setPassword(customer.getPassword());
            _customer.setEmail(customer.getEmail());
            _customer.setAdmin(customer.getAdmin());
            _customer.setPhone(customer.getPhone());
            return customerRepository.save(_customer);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Request> findRequests() {
        return new ArrayList<>(requestRepository.findAll());
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Request findRequest(@PathVariable("id") Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Request createRequest(@RequestBody Request request) {
        if (request.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Id of customer you want to find is null");
        }
        var customer = customerRepository.findById(request.getCustomer().getId()).orElseThrow(() ->
                new IllegalArgumentException("Cannot find customer with this id: " + request.getCustomer().getId()));
        try {
            return requestRepository.save(new Request(request.getTitle(), request.getDescription(), request.getStatus() , customer));
        } catch (Exception e) {
            return request;
        }
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Request updateRequest(@PathVariable("id") Long id, @RequestBody Request request) {
        Optional<Request> requestData = requestRepository.findById(id);
        if (requestData.isPresent()) {
            Request _request = requestData.get();
            _request.setDescription(request.getDescription());
            _request.setTitle(request.getTitle());
            _request.setStatus(request.getStatus());
            if (_request.getCustomer().getId() == null) {
                throw new IllegalArgumentException("Id of Film you want to find is null");
            }
            var customer = customerRepository.findById(request.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + _request.getCustomer().getId()));
            _request.setCustomer(customer);
            return requestRepository.save(_request);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/requests/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteRequest(@PathVariable("id") Long id) {
        try {
            Request fetchedChild = requestRepository.findById(id).get();
            requestRepository.delete(fetchedChild);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(value = "/subscriptions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subscription> findSubscriptions() {
        return new ArrayList<>(subscriptionRepository.findAll());
    }

    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Subscription findSubscription(@PathVariable("id") Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/subscriptions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Subscription createSubscription(@RequestBody Subscription subscription) {

        if (subscription.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Id is null");
        }
        var customer = customerRepository.findById(subscription.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + subscription.getCustomer().getId()));
        try {
            return subscriptionRepository.save(new Subscription(subscription.getStartDate(), subscription.getEndDate(), customer));
        } catch (Exception e) {
            return subscription;
        }
    }

    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Subscription updateSubscription(@PathVariable("id") Long id, @RequestBody Subscription subscription) {
        Optional<Subscription> subscriptionData = subscriptionRepository.findById(id);
        if (subscriptionData.isPresent()) {
            Subscription _subscription = subscriptionData.get();
            _subscription.setStartDate(subscription.getStartDate());
            _subscription.setEndDate(subscription.getEndDate());
            if (_subscription.getCustomer().getId() == null) {
                throw new IllegalArgumentException("Id of Film you want to find is null");
            }
            var customer = customerRepository.findById(subscription.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + _subscription.getCustomer().getId()));
            _subscription.setCustomer(customer);
            return subscriptionRepository.save(_subscription);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/subscriptions/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteSubscription(@PathVariable("id") Long id) {
        try {
            subscriptionRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(value = "/coworkings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CoWorking> findCoWorkings() {
        return new ArrayList<>(coWorkingRepository.findAll());
    }

    @RequestMapping(value = "/coworkings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CoWorking findCoWorking(@PathVariable("id") Long id) {
        return coWorkingRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/coworkings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CoWorking createCoWorking(@RequestBody CoWorking coWorking) {

        if (coWorking.getCustomer().getId() == null) {
            throw new IllegalArgumentException("Id of Film you want to find is null");
        }
        var customer = customerRepository.findById(coWorking.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + coWorking.getCustomer().getId()));
        try {
            return coWorkingRepository.save(new CoWorking(coWorking.getName(), coWorking.getAddress(), customer));
        } catch (Exception e) {
            return coWorking;
        }
    }

    @RequestMapping(value = "/coworkings/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CoWorking updateCoWorking(@PathVariable("id") Long id, @RequestBody CoWorking coWorking) {
        Optional<CoWorking> coWorkingData = coWorkingRepository.findById(id);
        if (coWorkingData.isPresent()) {
            CoWorking _coworking = coWorkingData.get();
            _coworking.setName(coWorking.getName());
            _coworking.setAddress(coWorking.getAddress());
            if (_coworking.getCustomer().getId() == null) {
                throw new IllegalArgumentException("Id of Film you want to find is null");
            }
            var customer = customerRepository.findById(coWorking.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + _coworking.getCustomer().getId()));
            _coworking.setCustomer(customer);
            return coWorkingRepository.save(_coworking);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/coworkings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteCoWorking(@PathVariable("id") Long id) {
        try {
            coWorkingRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> findRooms() {
        return new ArrayList<>(roomRepository.findAll());
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room findRoom(@PathVariable("id") Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room createRoom(@RequestBody Room room) {

        if (room.getCoWorking().getId() == null) {
            throw new IllegalArgumentException("Id of Film you want to find is null");
        }
        var coWorking = coWorkingRepository.findById(room.getCoWorking().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + room.getCoWorking().getId()));
        try {
            return roomRepository.save(new Room(room.getName(), room.getHumanity(), room.getTemperature(), room.getLighting(), room.getSquare(), room.getDescription(), coWorking));
        } catch (Exception e) {
            return room;
        }
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room updateRoom(@PathVariable("id") Long id, @RequestBody Room room) {
        Optional<Room> roomData = roomRepository.findById(id);
        if (roomData.isPresent()) {
            Room _room = roomData.get();
            _room.setName(room.getName());
            _room.setDescription(room.getDescription());
            _room.setHumanity(room.getHumanity());
            _room.setLighting(room.getLighting());
            _room.setTemperature(room.getTemperature());
            _room.setSquare(room.getSquare());
            if (_room.getCoWorking().getId() == null) {
                throw new IllegalArgumentException("Id of Film you want to find is null");
            }
            var coWorking = coWorkingRepository.findById(room.getCoWorking().getId()).orElseThrow(() -> new IllegalArgumentException("Cannot find film with this id: " + _room.getCoWorking().getId()));
            _room.setCoWorking(coWorking);
            return roomRepository.save(_room);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus deleteRoom(@PathVariable("id") Long id) {
        try {
            roomRepository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
