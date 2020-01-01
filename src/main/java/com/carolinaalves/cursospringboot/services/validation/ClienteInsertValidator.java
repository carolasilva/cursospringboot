package com.carolinaalves.cursospringboot.services.validation;

import com.carolinaalves.cursospringboot.domain.Cliente;
import com.carolinaalves.cursospringboot.domain.enums.TipoCliente;
import com.carolinaalves.cursospringboot.dto.NovoClienteDto;
import com.carolinaalves.cursospringboot.repository.ClienteRepository;
import com.carolinaalves.cursospringboot.resources.exceptions.FieldMessage;
import com.carolinaalves.cursospringboot.services.validation.utils.CpfOrCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NovoClienteDto> {

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public void initialize(ClienteInsert ann) {
  }
  @Override
  public boolean isValid(NovoClienteDto novoClienteDto, ConstraintValidatorContext context) {
    List<FieldMessage> list = new ArrayList<>();

    if (novoClienteDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !CpfOrCnpjValidator.isValidCPF(novoClienteDto.getCpfOuCnpj()))
      list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

    if (novoClienteDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !CpfOrCnpjValidator.isValidCNPJ(novoClienteDto.getCpfOuCnpj()))
      list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

    Cliente cliente = clienteRepository.findByEmail(novoClienteDto.getEmail());
    if (cliente != null)
      list.add(new FieldMessage("email", "Email já existente"));

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage())
          .addPropertyNode(e.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}