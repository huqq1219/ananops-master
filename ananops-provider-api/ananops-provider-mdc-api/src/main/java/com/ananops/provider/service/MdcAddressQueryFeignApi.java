

package com.ananops.provider.service;

import com.ananops.provider.model.dto.AddressDTO;
import com.ananops.provider.service.hystrix.MdcAddressQueryFeignHystrix;
import com.ananops.security.feign.OAuth2FeignAutoConfiguration;
import com.ananops.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The interface Mdc product query feign api.
 *
 * @author ananops.com @gmail.com
 */
@FeignClient(value = "ananops-provider-mdc", configuration = OAuth2FeignAutoConfiguration.class, fallback = MdcAddressQueryFeignHystrix.class)
public interface MdcAddressQueryFeignApi {

	/**
	 * Select by id wrapper.
	 *
	 * @param addressId the address id
	 *
	 * @return the wrapper
	 */
	@PostMapping(value = "/api/address/getById/{addressId}")
	Wrapper<AddressDTO> getById(@PathVariable("addressId") Long addressId);
}
