package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.google.common.base.Objects;
import org.assertj.core.api.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTests {

    @Test
    public void comparingTrelloCardToCardDtoMapperTest() {
//        Given
        TrelloCard trelloCard = new TrelloCard("kolacja", "zrpbic k.", "2", "3");
        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
//        Then
        assertThat(Objects.equal(trelloCard, trelloCardDto));
    }

    @Test
    public void comparingTrelloCardDtoToCardMapperTest() {
//        Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("kolacja", "zrpbic k.", "2", "3");
        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
//        Then
        assertThat(Objects.equal(trelloCard, trelloCardDto));
    }

    @Test
    public void comparingTrelloBoardToBoardDtoMapperTest() {
//        Given
        TrelloList trelloList1 = new TrelloList("1", "sniadanie", true);
        TrelloList trelloList2 = new TrelloList("1", "obiad", true);
        List<TrelloList> listsMeals = new ArrayList<TrelloList>();
        listsMeals.add(trelloList1);
        listsMeals.add(trelloList2);

        TrelloList trelloList3 = new TrelloList("1", "biegi", true);
        TrelloList trelloList4 = new TrelloList("1", "przysiady", true);
        List<TrelloList> listsExercises = new ArrayList<TrelloList>();
        listsExercises.add(trelloList3);
        listsExercises.add(trelloList4);

        TrelloBoard trelloBoard1 = new TrelloBoard("1", "posilki", listsMeals);
        TrelloBoard trelloBoard2 = new TrelloBoard("1", "posilki", listsExercises);

        List<TrelloBoard> trelloBoards = new ArrayList<TrelloBoard>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
//        Then
        assertThat(Objects.equal(trelloBoards, trelloBoardsDto));
    }

    @Test
    public void comparingTrelloBoardDtoToBoardMapperTest() {
//            Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "sniadanie", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("1", "obiad", true);
        List<TrelloListDto> listsMeals = new ArrayList<TrelloListDto>();
        listsMeals.add(trelloListDto1);
        listsMeals.add(trelloListDto2);

        TrelloListDto trelloListDto3 = new TrelloListDto("1", "biegi", true);
        TrelloListDto trelloListDto4 = new TrelloListDto("1", "przysiady", true);
        List<TrelloListDto> listsExercises = new ArrayList<TrelloListDto>();
        listsExercises.add(trelloListDto3);
        listsExercises.add(trelloListDto4);

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "posilki", listsMeals);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("1", "posilki", listsExercises);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<TrelloBoardDto>();
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
//        Then
        assertThat(Objects.equal(trelloBoards, trelloBoardsDto));
    }

    @Test
    public void comparingTrelloListToListDtoMapperTest() {

//        Given
        TrelloList trelloList1 = new TrelloList("1", "sniadanie", true);
        TrelloList trelloList2 = new TrelloList("1", "obiad", true);
        List<TrelloList> listsMeals = new ArrayList<TrelloList>();
        listsMeals.add(trelloList1);
        listsMeals.add(trelloList2);

        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        List<TrelloListDto> listsMealsDto = trelloMapper.mapToListDto(listsMeals);
//        Then
        assertThat(Objects.equal(listsMealsDto, listsMeals));
    }

    @Test
    public void comparingTrelloListDtoToListMapperTest() {

//        Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "sniadanie", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("1", "obiad", true);
        List<TrelloListDto> listsMealsDto = new ArrayList<TrelloListDto>();
        listsMealsDto.add(trelloListDto1);
        listsMealsDto.add(trelloListDto2);

        TrelloMapper trelloMapper = new TrelloMapper();
//        When
        List<TrelloList> listsMeals = trelloMapper.mapToList(listsMealsDto);
//        Then
        assertThat(Objects.equal(listsMealsDto, listsMeals));
    }
}

