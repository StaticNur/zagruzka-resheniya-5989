package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.model.entity.Face;
import ru.soglasie.testing_hypotheses.service.FaceService;

import java.util.List;

@RestController
@RequestMapping("/api/faces")
public class FaceController {

    @Autowired
    private FaceService faceService;

    @GetMapping
    public List<Face> getAllFaces() {
        return faceService.getAllFaces();
    }

    @PostMapping
    public Face createFace(@RequestBody Face face) {
        return faceService.createFace(face);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Face> getFaceById(@PathVariable Long id) {
        return faceService.getFaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Face> updateFace(@PathVariable Long id, @RequestBody Face faceDetails) {
        return ResponseEntity.ok(faceService.updateFace(id, faceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFace(@PathVariable Long id) {
        return faceService.deleteFace(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}


