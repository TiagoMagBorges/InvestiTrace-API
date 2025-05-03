package br.com.investitrace.investitraceapi.controller;

import br.com.investitrace.investitraceapi.domain.model.Relation;
import br.com.investitrace.investitraceapi.service.RelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/relations")
public class RelationController {

    private final RelationService relationService;

    public RelationController(RelationService relationService) {
        this.relationService = relationService;
    }

    @GetMapping("/{relationId}")
    public ResponseEntity<Relation> getRelationById(@PathVariable Long relationId) {
        Relation relation = relationService.getRelationById(relationId);
        return ResponseEntity.ok(relation);
    }

    @GetMapping
    public ResponseEntity<List<Relation>> getAllRelations() {
        List<Relation> relations = relationService.getAllRelations();
        return ResponseEntity.ok(relations);
    }

    @PostMapping
    public ResponseEntity<Relation> createRelation(@RequestBody Relation relation) {
        Relation createdRelation = relationService.createRelation(relation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{relationId}")
                .buildAndExpand(createdRelation.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdRelation);
    }

    @PutMapping("/{relationId}")
    public ResponseEntity<Void> updateRelation(@PathVariable Long relationId, @RequestBody Relation relation) {
        relation.setId(relationId);
        relationService.updateRelation(relation);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{relationId}/user/{userId}")
    public ResponseEntity<Void> deleteRelation(
            @PathVariable Long userId,
            @PathVariable Long relationId) {
        relationService.deleteRelation(relationId, userId);
        return ResponseEntity.noContent().build();
    }


}
