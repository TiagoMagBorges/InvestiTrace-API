package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.Relation;
import br.com.investitrace.investitraceapi.domain.repository.RelationRepository;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.RelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    private final RelationRepository relationRepository;

    private final UserRepository userRepository;

    public RelationServiceImpl(RelationRepository relationRepository, UserRepository userRepository) {
        this.relationRepository = relationRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Relation createRelation(Relation relation) {
        validateRelationDoesNotExist(relation);
        validateUserExists(relation.getUserId());
        return relationRepository.save(relation);
    }

    @Override
    public void updateRelation(Relation relation) {
        Relation existingRelation = getRelationOrThrow(relation.getId());

        if (!existingRelation.getUserId().equals(relation.getUserId()))
            throw new IllegalArgumentException("UserId mismatch. Cannot update relation with a different user.");

        existingRelation.setName(relation.getName());
        existingRelation.setDescription(relation.getDescription());
        existingRelation.setOriginId(relation.getOriginId());
        existingRelation.setOriginType(relation.getOriginType());
        existingRelation.setTargetId(relation.getTargetId());
        existingRelation.setTargetType(relation.getTargetType());

        relationRepository.save(existingRelation);
    }

    @Override
    public void deleteRelation(Long relationId, Long userId) {
        if(!relationRepository.existsById(relationId))
            throw new IllegalArgumentException("Relation not found with id: " + relationId);

        if(!userRepository.existsById(userId))
            throw new IllegalArgumentException("User not found with id: " + userId);

        relationRepository.deleteById(relationId);
    }

    @Override
    public Relation getRelationById(Long relationId) {
        return getRelationOrThrow(relationId);
    }

    @Override
    public List<Relation> getAllRelations() {
        return relationRepository.findAll();
    }

    private void validateRelationDoesNotExist(Relation relation) {
        if (relationRepository.existsById(relation.getId()))
            throw new IllegalArgumentException("Relation already exists with id: " + relation.getId());
    }

    private Relation getRelationOrThrow(Long relationId) {
        return relationRepository.findById(relationId)
                .orElseThrow(() -> new IllegalArgumentException("Relation not found with id: " + relationId));
    }

    private void validateUserExists(Long userId) {
        if (!userRepository.existsById(userId))
            throw new IllegalArgumentException("User does not exist with id: " + userId);
    }
}
