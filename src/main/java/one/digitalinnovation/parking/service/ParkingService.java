package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();
    static {
        var id1 = getUUID();
        var id2 = getUUID();
        Parking parking1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "PRETO");
        Parking parking2 = new Parking(id2, "DMS-2222", "SP", "CORSA", "CINZA");

        parkingMap.put(id1, parking1);
        parkingMap.put(id2, parking2);
    }
    @GetMapping
    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }
}
