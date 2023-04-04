package org.prog.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultsDto {
    private List<UserDto> results;

//    public SearchResultsDto() {
//
//    }
//
//    public SearchResultsDto(List<UserDto> results) {
//        this.results = results;
//    }
//
//    public List<UserDto> getResults() {
//        return results;
//    }
//
//    public void setResults(List<UserDto> results) {
//        this.results = results;
//    }
}
