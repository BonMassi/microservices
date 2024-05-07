package com.bon.accounts.services.impl;

import com.bon.accounts.dto.AccountsDto;
import com.bon.accounts.dto.CardsDto;
import com.bon.accounts.dto.CustomerDetailsDto;
import com.bon.accounts.dto.LoansDto;
import com.bon.accounts.entities.Accounts;
import com.bon.accounts.entities.Customer;
import com.bon.accounts.exception.ResourceNotFoundException;
import com.bon.accounts.mappers.AccountsMapper;
import com.bon.accounts.mappers.CustomerMapper;
import com.bon.accounts.repository.AccountsRepository;
import com.bon.accounts.repository.CustomerRepository;
import com.bon.accounts.services.ICustomersService;
import com.bon.accounts.services.client.CardsFeignClient;
import com.bon.accounts.services.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString())
        );

       CustomerDetailsDto customerDetailsDto =  CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());

       customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

       ResponseEntity< LoansDto > loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
       customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

       ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
       customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return  customerDetailsDto;
    }
}
