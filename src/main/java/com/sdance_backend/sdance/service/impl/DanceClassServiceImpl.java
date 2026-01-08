package com.sdance_backend.sdance.service.impl;

import com.sdance_backend.sdance.dto.*;
import com.sdance_backend.sdance.entity.DanceClass;
import com.sdance_backend.sdance.exception.CustomException;
import com.sdance_backend.sdance.mapper.DanceClassMapper;
import com.sdance_backend.sdance.messages.errors.DanceClassError;
import com.sdance_backend.sdance.messages.errors.GenericError;
import com.sdance_backend.sdance.repository.DanceClassRepository;
import com.sdance_backend.sdance.service.IDanceClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DanceClassServiceImpl implements IDanceClassService {

    private final DanceClassRepository danceClassRepository;
    private final InstructorServiceImpl instructorService;

    private final DanceClassMapper danceClassMapper;

    @Override
    public List<DanceClassDTO> getAllDanceClass() {
        List<DanceClass> danceClassDTOList = (List<DanceClass>) danceClassRepository.findAll();

        if (danceClassDTOList.isEmpty()) {
            throw new CustomException(DanceClassError.DANCE_CLASS_LIST_EMPTY);
        }

        return danceClassMapper.toDTOList(danceClassDTOList);
    }

    @Override
    @Transactional(readOnly = true)
    public DanceClassDTO getDanceClassById(UUID id) {
        return  danceClassMapper.toDTO(danceClassRepository.findById(id).get());
    }

    @Override
    @Transactional
    public DanceClassDTO createDanceClass(DanceClassRequestDTO danceClassRequestDTO) {
       try{
            DanceClass danceClass = danceClassMapper.toEntity(danceClassRequestDTO);
            danceClass.setInstructor(instructorService.getInstructor(danceClassRequestDTO.getInstructorId()));
            danceClass.setStudents(new ArrayList<>());

           danceClassRepository.save(danceClass);

           return danceClassMapper.toDTO(danceClass);

       } catch (Exception ex) {
           throw new CustomException(DanceClassError.DANCE_CLASS_CREATE_ERROR,
                   ex.getMessage());
       }
    }

    @Override
    @Transactional
    public DanceClassDTO updateDanceClass(DanceClassRequestDTO danceClassRequestDTO, UUID id) {
        try{
            DanceClass danceClass = getDanceClass(id);

            danceClass.setInstructor(instructorService.getInstructor(danceClassRequestDTO.getInstructorId()));

            danceClassMapper.updateFromDTO(danceClassRequestDTO, danceClass);
            danceClassRepository.save(danceClass);

            return danceClassMapper.toDTO(danceClass);

        } catch (Exception ex) {
            throw new CustomException(DanceClassError.DANCE_CLASS_UPDATE_ERROR,
                    ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteDanceClass(UUID id) {
        try {
            DanceClass danceClass = getDanceClass(id);
            danceClassRepository.delete(danceClass);
        } catch (Exception ex) {
            throw new CustomException(DanceClassError.DANCE_CLASS_DELETE_ERROR,
                    ex.getMessage());
        }
    }

    @Override
    public PageResponseDTO<DanceClassDTO> searchDanceClass(SearchTermDTO searchRequest, int page, int size) {

        // Validacion de pag y tamaño
        if (page < 0) {
            throw new CustomException(GenericError.INVALID_PAGE_PARAMETER_ERROR
            );
        }

        if (size < 1 || size > 100) {
            throw new CustomException(GenericError.INVALID_SIZE_PARAMETER_ERROR
            );
        }

        int pageNumber = Math.max(0, page);

        try {
            Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC, "className"));

            //  busco la descripcion en los atributos de dance class
            Specification<DanceClass> spec = (root, query, cb) -> {
                if (searchRequest != null && searchRequest.getDescription() != null && !searchRequest.getDescription().trim().isEmpty()) {
                    String term = "%" + searchRequest.getDescription().toLowerCase() + "%";
                    return cb.or(
                            cb.like(cb.lower(root.get("className")), term)
                    );
                }
                return null;
            };


            Page<DanceClass> danceClassPage = danceClassRepository.findAll(spec, pageable);

            List<DanceClassDTO> danceClasses = danceClassPage.getContent().stream()
                    .map(danceClassMapper::toDTO)
                    .collect(Collectors.toList());

            return new PageResponseDTO<>(
                    danceClasses,
                    danceClassPage.getTotalElements(),
                    page
            );

        } catch (Exception e) {
            throw new CustomException(
                    GenericError.SEARCH_ERROR
            );
        }
    }

    public DanceClass getDanceClass(UUID id) {
        return danceClassRepository.findById(id).orElseThrow(() -> new CustomException(DanceClassError.DANCE_CLASS_NOT_FOUND));
    }
}
