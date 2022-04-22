package br.com.sysmap.framework.adapters.out;

import br.com.sysmap.domain.PortabilityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PortabilityRepository extends JpaRepository<PortabilityRequest, UUID> {
}
