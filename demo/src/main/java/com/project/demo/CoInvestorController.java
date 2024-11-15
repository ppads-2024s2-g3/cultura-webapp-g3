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
public class CoInvestorController {

    @Autowired
    private CoInvestorRepository investorRepository;

    public CoInvestorController() {
    }

    // Retorna todos os CoInvestors
    @GetMapping("/api/co-investors")
    public List<CoInvestor> getCoInvestors(@RequestParam Optional<Long> algumId) {
        List<CoInvestor> coInvestors = investorRepository.findAll()
                .stream()
                .filter(investor -> investor instanceof CoInvestor)
                .map(investor -> (CoInvestor) investor)
                .collect(Collectors.toList());

        return coInvestors;
    }

    // Retorna um CoInvestor específico pelo ID
    @GetMapping("/api/co-investors/{id}")
    public Optional<CoInvestor> getCoInvestor(@PathVariable long id) {
        return investorRepository.findById(id)
                .filter(investor -> investor instanceof CoInvestor)
                .map(investor -> (CoInvestor) investor);
    }

    // Cria um novo CoInvestor
    @PostMapping("/api/co-investors")
    public CoInvestor createCoInvestor(@RequestBody CoInvestor coInvestor) {
        return investorRepository.save(coInvestor);
    }

    // Atualiza um CoInvestor específico pelo ID
    @PutMapping("/api/co-investors/{investorId}")
    public Optional<CoInvestor> updateCoInvestor(@RequestBody CoInvestor coInvestorRequest, 
                                                 @PathVariable long investorId) {
        Optional<CoInvestor> opt = investorRepository.findById(investorId)
                .filter(investor -> investor instanceof CoInvestor)
                .map(investor -> (CoInvestor) investor);

        if (opt.isPresent()) {
            if (coInvestorRequest.getId().equals(investorId)) {
                investorRepository.save(coInvestorRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do co-investidor com id " + investorId);
    }

    // Deleta um CoInvestor específico pelo ID
    @DeleteMapping("/api/co-investors/{id}")
    public void deleteCoInvestor(@PathVariable long id) {
        investorRepository.deleteById(id);
    }
}
