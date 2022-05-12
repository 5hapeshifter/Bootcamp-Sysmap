//package br.com.sysmap.framework.adapters.in.feign;
//
//import br.com.sysmap.framework.adapters.in.dtos.PortabilityResponseStatus;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.UUID;
//
//@FeignClient(name = "src-ms-portability", url = "http://localhost:8080/portability")
//public interface PortabilityFeignClient {
//
//    @PostMapping(value = "src-ms-portability/{portabilityId}", produces = "application/json", consumes = "application/json")
//    public ResponseEntity<PortabilityResponseStatus> updatePortability(@PathVariable(name = "portabilityId") UUID uuid,
//                                                                       @RequestBody @Valid PortabilityResponseStatus portabilityRequest);
//
//
//}
