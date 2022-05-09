package br.com.sysmap.framework.adapters.out;

import br.com.sysmap.domain.PortabilityPublishRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PortabilityConsumerRepository extends JpaRepository<PortabilityPublishRequest, UUID> {

}
