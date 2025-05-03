package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.Relation;

import java.util.List;

public interface RelationService {
    Relation createRelation(Relation relation);

    void updateRelation(Relation relation);

    void deleteRelation(Long relationId, Long userId);

    Relation getRelationById(Long relationId);

    List<Relation> getAllRelations();
}
