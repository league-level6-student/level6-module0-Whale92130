package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();
    
    @Mock
    Rocketship rocket;
    @Mock
    Astronaut astronaut;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	underTest.setRocketship(rocket);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    	
        //when
    	underTest.prepareAstronaut(astronaut);
        //then
    	verify(astronaut, times(1)).train();
    	verify(rocket, times(1)).loadOccupant(any());
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	String destination = "Mars";
        //when
    	when(rocket.isLoaded()).thenReturn(true);
    	
    	underTest.launchRocket(destination);
        //then
    	verify(rocket, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
    	 //given
    	String destination = "Moon";
    	
        //when
    	when(rocket.isLoaded()).thenReturn(true);
    	
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
    	assertEquals(exceptionThrown.getMessage(), "Destination is unavailable");
    	verify(rocket, never()).launch();
    }

    @Test
    void itShouldThrowNotLoaded() {
    	//given
    	String destination = "Mars";
        //when
    	when(rocket.isLoaded()).thenReturn(false);
    	
        //then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(destination));
    	assertEquals(exceptionThrown.getMessage(), "Rocketship is not loaded");
    	verify(rocket, never()).launch();

    }
}