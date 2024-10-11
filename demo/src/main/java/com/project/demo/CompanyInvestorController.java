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
class CompanyInvestorController {

    @Autowired
    private InvestorRepository investorRepository;

    public CompanyInvestorController() {
    }

    @GetMapping("/api/company-investors")
    Iterable<CompanyInvestor> getCompanyInvestors(@RequestParam Optional<Long> algumId) {
        List<Investor> investors = (List<Investor>) investorRepository.findAll();
        return investors.stream()
                .filter(investor -> investor instanceof CompanyInvestor)
                .map(investor -> (CompanyInvestor) investor)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/company-investors/{id}")
    Optional<CompanyInvestor> getCompanyInvestor(@PathVariable long id) {
        return investorRepository.findById(id).map(investor -> (CompanyInvestor) investor);
    }

    @PostMapping("/api/company-investors")
    CompanyInvestor createCompanyInvestor(@RequestBody CompanyInvestor companyInvestor) {
        return investorRepository.save(companyInvestor);
    }

    @PutMapping("/api/company-investors/{investorId}")
    Optional<CompanyInvestor> updateCompanyInvestor(@RequestBody CompanyInvestor companyInvestorRequest, @PathVariable long investorId) {
        Optional<CompanyInvestor> opt = investorRepository.findById(investorId).map(investor -> (CompanyInvestor) investor);
        if (opt.isPresent()) {
            if (companyInvestorRequest.getId().equals(investorId)) {
                investorRepository.save(companyInvestorRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do investidor da empresa com id " + investorId);
    }

    @DeleteMapping(value = "/api/company-investors/{id}")
    void deleteCompanyInvestor(@PathVariable long id) {
        investorRepository.deleteById(id);
    }
}
