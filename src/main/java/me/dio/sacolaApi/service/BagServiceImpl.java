package me.dio.sacolaApi.service;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaApi.enumeration.PaymentForm;
import me.dio.sacolaApi.exceptions.BusinessException;
import me.dio.sacolaApi.model.Bag;
import me.dio.sacolaApi.model.Item;
import me.dio.sacolaApi.model.Restaurant;
import me.dio.sacolaApi.repository.BagRepository;
import me.dio.sacolaApi.repository.ItemRepository;
import me.dio.sacolaApi.repository.ProductRepository;
import me.dio.sacolaApi.resource.dto.ItemDto;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
	private final BagRepository bagRepository;
	private final ProductRepository productRepository;
	private final ItemRepository itemRepository;
	
	@Override
	public Item addItem(ItemDto itemDto) {
		Bag bag = seeBag(itemDto.getIdBag());
		
		if (bag.isClosed()) {
			throw new BusinessException("This bag is closed!");
		}
		
		Item item = Item.builder()
		.quantity(itemDto.getQuantity())
		.bag(bag)
		.product(productRepository.findById(itemDto.getIdProduct()).orElseThrow(
				() -> {
					throw new BusinessException("This product does not exist!");
				}
				))
		.build();
		
		List<Item> items = bag.getItems();
		
		if (items.isEmpty()) {
			items.add(item);
		} else {
			Restaurant currentRestaurant = items.get(0).getProduct().getRestaurant();
			Restaurant productRestaurant = item.getProduct().getRestaurant();
			
			if (currentRestaurant.equals(productRestaurant)) {
				items.add(item);
			} else {
				throw new BusinessException("It's not possible to add products from different restaurants. Empty or close the bag!");
			}
		}
		
		bagRepository.save(bag);
		return item;
	}

	@Override
	public Bag seeBag(Long id) {
		return bagRepository.findById(id).orElseThrow(
					() -> {
						throw new BusinessException("This bag does not exists");
					}
				);
	}

	@Override
	public Bag closeBag(Long id, int paymentFormNumber) {
		Bag bag = seeBag(id);
		
		if (bag.getItems().isEmpty()) {
			throw new BusinessException("Include items in the bag!");
		}
		
		PaymentForm paymentForm = paymentFormNumber == 0 ? PaymentForm.MONEY : PaymentForm.MACHINE;
		
		bag.setPaymentForm(paymentForm);
		bag.setClosed(true);
		
		return bagRepository.save(bag);
	}

}
