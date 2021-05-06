package br.com.neartech.nearby.core;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public abstract class BaseController<T, ID>{

    protected abstract BaseService<T, ID> getService();

    @GetMapping
    public Page<T> find(@RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "20") Integer size){
        return getService().findAll(
                PageRequest.of(page, size)
        );
    }

    @GetMapping("{id}")
    public T findById(@PathVariable ID id){
        //todo por em properties a mensagem, ou em messages, sei la
        return Optional.ofNullable(getService().findById(id))
                .orElseThrow(() -> new EmptyResultDataAccessException(
                        String.format("Não foi localizado registro com o parametro %s informado", id), 1
                ));
    }

    @PostMapping
    public T save(@Valid @RequestBody T entity) {
        return getService().save(entity);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable ID id){
        getService().delete(
                getService().findById(id)
        );
    }

}
