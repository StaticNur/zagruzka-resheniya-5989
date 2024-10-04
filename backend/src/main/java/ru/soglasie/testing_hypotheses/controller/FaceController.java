//package ru.soglasie.testing_hypotheses.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.soglasie.testing_hypotheses.model.entity.Face;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/faces")
//public class FaceController {
//
//    @Autowired
//    private FaceRepository faceRepository;
//
//    @GetMapping
//    public List<Face> getAllFaces() {
//        return faceRepository.findAll();
//    }
//
//    @PostMapping
//    public Face createFace(@RequestBody Face face) {
//        return faceRepository.save(face);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Face> getFaceById(@PathVariable Long id) {
//        return faceRepository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Face> updateFace(@PathVariable Long id, @RequestBody Face faceDetails) {
//        return faceRepository.findById(id)
//                .map(face -> {
//                    face.setType(faceDetails.getType());
//                    face.setFirstName(faceDetails.getFirstName());
//                    face.setLastName(faceDetails.getLastName());
//                    face.setInn(faceDetails.getInn());
//                    return ResponseEntity.ok(faceRepository.save(face));
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteFace(@PathVariable Long id) {
//        return faceRepository.findById(id)
//                .map(face -> {
//                    faceRepository.delete(face);
//                    return ResponseEntity.ok().build();
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
//
