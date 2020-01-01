package com.carolinaalves.cursospringboot.services.validation;

import com.carolinaalves.cursospringboot.domain.enums.TipoCliente;
import com.carolinaalves.cursospringboot.dto.NovoClienteDto;
import com.carolinaalves.cursospringboot.resources.exceptions.FieldMessage;
import com.carolinaalves.cursospringboot.services.validation.utils.CpfOrCnpjValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, NovoClienteDto> {
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

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage())
          .addPropertyNode(e.getFieldName()).addConstraintViolation();
    }
    return list.isEmpty();
  }
}