package com.carolinaalves.cursospringboot.services.validation;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.dto.ClienteDto;
import com.carolinaalves.cursospringboot.repository.ClienteRepository;
import com.carolinaalves.cursospringboot.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {

  @Autowired
  private HttpServletRequest httpServletRequest;

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void initialize(ClienteUpdate ann) {
  }
  @Override
  public boolean isValid(ClienteDto novoClienteDto, ConstraintValidatorContext context) {
    Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Integer uriId = Integer.parseInt(map.get("id"));

    List<FieldMessage> list = new ArrayList<>();

    Cliente cliente = clienteRepository.findByEmail(novoClienteDto.getEmail());
    if (cliente != null && !cliente.getId().equals(uriId))
      list.add(new FieldMessage("email", "Email já existente"));

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage())
          .addPropertyNode(e.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}