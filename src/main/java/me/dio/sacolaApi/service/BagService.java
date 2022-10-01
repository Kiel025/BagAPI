package me.dio.sacolaApi.service;

import me.dio.sacolaApi.model.Bag;
import me.dio.sacolaApi.model.Item;
import me.dio.sacolaApi.resource.dto.ItemDto;

public interface BagService {
	
	Item addItem(ItemDto itemDto);
	Bag seeBag(Long id);
	Bag closeBag(Long id, int paymentForm);
	
}
