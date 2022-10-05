package me.dio.sacolaApi.resource;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaApi.model.Bag;
import me.dio.sacolaApi.model.Item;
import me.dio.sacolaApi.resource.dto.ItemDto;
import me.dio.sacolaApi.service.BagService;

@Api(value = "/ifood-dev-week/bags", tags = {""})
@RestController
@RequestMapping("/ifood-dev-week/bags")
@RequiredArgsConstructor
public class BagResource {

	private final BagService bagService;

	@PostMapping
	public Item addItem(@RequestBody ItemDto itemDto) {
		return bagService.addItem(itemDto);
	}

	@GetMapping("/{id}")
	public Bag seeBag(@PathVariable("id") Long id) {
		return bagService.seeBag(id);
	}

	@PatchMapping("/closeBag/{id}")
	public Bag closeBag(@PathVariable("id") Long id, @RequestParam("paymentForm") int paymentForm) {
		return bagService.closeBag(id, paymentForm);
	}
}
