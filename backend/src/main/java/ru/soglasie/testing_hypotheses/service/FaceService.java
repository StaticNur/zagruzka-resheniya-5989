package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.soglasie.testing_hypotheses.model.entity.Face;
import ru.soglasie.testing_hypotheses.repository.FaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FaceService {
    @Autowired
    private FaceRepository faceRepository;

    public List<Face> getAllFaces() {
        return faceRepository.findAll();
    }

    @Transactional
    public Face createFace(Face face) {
        return faceRepository.save(face);
    }

    public Optional<Face> getFaceById(Long id) {
        return faceRepository.findById(id);
    }

    @Transactional
    public Face updateFace(Long id, Face faceDetails) {
        return faceRepository.findById(id)
                .map(face -> {
                    face.setType(faceDetails.getType());
                    face.setFirstName(faceDetails.getFirstName());
                    face.setLastName(faceDetails.getLastName());
                    face.setInn(faceDetails.getInn());
                    return faceRepository.save(face);
                })
                .orElseThrow(() -> new NotFoundException("Face с ID " + id + " не найден"));
    }

    @Transactional
    public boolean deleteFace(Long id) {
        return faceRepository.findById(id)
                .map(face -> {
                    faceRepository.delete(face);
                    return true;
                })
                .orElse(false);
    }
}
