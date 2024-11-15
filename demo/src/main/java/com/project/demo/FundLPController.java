package com.project.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FundLPController {

    @Autowired
    private InvestorRepository InvestorRepository;

    public FundLPController() {
    }

    // Retorna todas as FundLP
    @GetMapping("/api/fund-LP")
    public List<FundLP> getFundLP(@RequestParam Optional<Long> algumId) {
        List<FundLP> FundLP = InvestorRepository.findAll()
                .stream()
                .filter(fund -> fund instanceof FundLP)
                .map(fund -> (FundLP) fund)
                .collect(Collectors.toList());

        return FundLP;
    }

    // Retorna uma FundLP específica pelo ID
    @GetMapping("/api/fund-LP/{id}")
    public Optional<FundLP> getFundLP(@PathVariable long id) {
        return InvestorRepository.findById(id)
                .filter(fund -> fund instanceof FundLP)
                .map(fund -> (FundLP) fund);
    }

    // Cria uma nova FundLP
    @PostMapping("/api/fund-LP")
    public FundLP createFundLP(@RequestBody FundLP FundLP) {
        return InvestorRepository.save(FundLP);
    }

    // Atualiza uma FundLP específica pelo ID
    @PutMapping("/api/fund-LP/{FundLPId}")
    public Optional<FundLP> updateFundLP(@RequestBody FundLP FundLPRequest,
                                                       @PathVariable long FundLPId) {
        Optional<FundLP> opt = InvestorRepository.findById(FundLPId)
                .filter(fund -> fund instanceof FundLP)
                .map(fund -> (FundLP) fund);

        if (opt.isPresent()) {
            if (FundLPRequest.getId().equals(FundLPId)) {
                InvestorRepository.save(FundLPRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados da estrutura de fundo com id " + FundLPId);
    }

    // Deleta uma FundLP específica pelo ID
    @DeleteMapping("/api/fund-LP/{id}")
    public void deleteFundLP(@PathVariable long id) {
        InvestorRepository.deleteById(id);
    }
}
