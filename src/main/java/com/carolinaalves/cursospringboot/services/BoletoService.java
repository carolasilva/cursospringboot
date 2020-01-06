package com.carolinaalves.cursospringboot.services;

import com.carolinaalves.cursospringboot.domain.PagamentoComBoleto;

import java.util.Calendar;
import java.util.Date;

public class BoletoService {
  public static void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(instante);
    cal.add(Calendar.DAY_OF_MONTH, 7);
    pagto.setDataVencimento(cal.getTime());
  }
}
