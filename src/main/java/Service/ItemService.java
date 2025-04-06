package Service;

import DTO.Request.ItemRequest;
import DTO.Response.ItemResponse;
import Entity.ItemEntity;
import Exception1.ResourceNotFoundException;
import MapperData.ItemMapper;
import Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public Page<ItemEntity> findAll(int page, int size,String sortBy) {
        page--;

        List<Sort.Order> sorts = new ArrayList<>();
        if (StringUtils.hasLength(sortBy)) {
            List<String> orderByFields = Arrays.asList(sortBy.split(","));
            orderByFields.forEach(orderByField -> {
                Pattern pattern = Pattern.compile("(\\w+)(:)(asc|desc)");
                Matcher matcher = pattern.matcher(orderByField);
                if (matcher.find()) {
                    if (matcher.group(3).equalsIgnoreCase("asc")) {
                        sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                    } else {
                        sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                    }
                }
            });
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sorts));

        return itemRepository.findAll(pageable);
    }

    public ItemEntity addItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    public void delete(int id) {
        Optional<ItemEntity> itemEntity = itemRepository.findById(id);
        if(!itemEntity.isPresent()) {
            throw new ResourceNotFoundException("Item not found");
        }
         itemRepository.delete(itemEntity.get());
    }

    public ItemResponse fixItem(ItemRequest itemRequest, int id) {


        Optional<ItemEntity> item = itemRepository.findById(id);
        if(!item.isPresent()){
            throw new ResourceNotFoundException("Item not found");
        }
        itemMapper.updateItemRequestFromItemEntity(itemRequest,item.get());
        System.out.println(item.get());
        
        ItemEntity savedItem = itemRepository.save(item.get());
        return itemMapper.toItemResponse(savedItem);
    }


}
