package com.onerivet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onerivet.model.entity.CoverageComponent;
import com.onerivet.model.entity.CoverageComponentCatalog;
import com.onerivet.model.entity.CoverageTypeCatalog;
import com.onerivet.repository.CoverageComponentCatalogRepository;
import com.onerivet.repository.CoverageComponentRepository;
import com.onerivet.repository.CoverageTypeCatalogRepository;

@Service
public class CoverageComponentService {

    @Autowired
    private CoverageTypeCatalogRepository coverageTypeCatalogRepository;

    @Autowired
    private CoverageComponentCatalogRepository catalogRepository;

    @Autowired
    private CoverageComponentRepository componentRepository;

    public List<String> getComponentNames(Integer coverageTypeCatalogId) {

        CoverageTypeCatalog typeCatalog =
                coverageTypeCatalogRepository.findById(coverageTypeCatalogId)
                        .orElseThrow();

        List<CoverageComponentCatalog> catalogs =
                catalogRepository.findByCoverageTypeId(typeCatalog.getCoverageTypeId());

        List<String> result = new ArrayList<>();

        for (CoverageComponentCatalog c : catalogs) {
            CoverageComponent comp =
                    componentRepository.findById(c.getCoverageComponentId())
                            .orElseThrow();

            result.add(comp.getComponent());
        }

        return result;
    }
}