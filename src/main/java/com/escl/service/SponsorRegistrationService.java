package com.escl.service;

import com.escl.dto.SponsorRegistrationRequestDTO;
import com.escl.dto.SponsorRegistrationResponseDTO;
import com.escl.mapper.SponsorRegistrationMapper;
import com.escl.model.SponsorRegistration;
import com.escl.repository.SponsorRegistrationRepository;
import com.escl.dto.PublicSponsorResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SponsorRegistrationService {

    private final SponsorRegistrationRepository repository;

    @Transactional
    public SponsorRegistrationResponseDTO registerSponsor(
            SponsorRegistrationRequestDTO dto) {

        SponsorRegistration sponsor =
                SponsorRegistrationMapper.toEntity(dto);

        sponsor.setStatus("PENDING");

        SponsorRegistration saved = repository.save(sponsor);

        return SponsorRegistrationMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<SponsorRegistrationResponseDTO> getAllSponsors() {

        return repository.findAll()
                .stream()
                .map(SponsorRegistrationMapper::toResponse)
                .toList();
    }

    @Transactional
    public String updateSponsorStatus(Long id, String status) {

        if (!List.of("APPROVED", "REJECTED").contains(status)) {
            throw new IllegalArgumentException("Invalid sponsor status.");
        }

        SponsorRegistration sponsor = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Sponsor not found."));

        sponsor.setStatus(status);
        repository.save(sponsor);

        return "Sponsor " + status.toLowerCase() + " successfully.";
    }

    public List<PublicSponsorResponseDTO> getApprovedPublicSponsors() {

        return repository
                .findAllByStatusIgnoreCaseOrderByCreatedAtDesc("APPROVED")
                .stream()
                .map(sponsor -> new PublicSponsorResponseDTO(
                        sponsor.getId(),
                        sponsor.getCompanyName(),
                        sponsor.getIndustry(),
                        sponsor.getWebsite(),
                        sponsor.getSponsorshipCategory(),
                        sponsor.getContributionType()
                ))
                .toList();
    }
}