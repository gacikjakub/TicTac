package pl.gacik.tictac;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class SignHolderTests {

    @Mock
    private Player player1 = Mockito.mock(Player.class);

    @Mock
    private Player player2 = Mockito.mock(Player.class);


    @DataProvider(name = "6PairsOfPlayerName")
    private Object[][] PairsOfPlayerName() {
        return new Object[][]{{"Zack", "Mark"}, {"Alex", "Ash"}, {"Jane", "Marry"}, {"Kenny", "Michael"}, {"Jack", "Fuck"},
                {"Andrew", "Joanna"}};
    }


    @Test(dataProvider = "6PairsOfPlayerName")
    public void shouldReturnProperSignOwner(String name1, String name2) {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        signHolder.attachPlayer(player2, Sign.CROSS);
        // then
        Assert.assertEquals(signHolder.getSignOwner(Sign.NOUGHT).get().getName(), player1.getName());
        Assert.assertEquals(signHolder.getSignOwner(Sign.CROSS).get().getName(), player2.getName());
    }

    @Test(dataProvider = "6PairsOfPlayerName", expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSpecifiedSignIsBooked(String name1, String name2) throws IllegalArgumentException {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when - then
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        signHolder.attachPlayer(player2, Sign.NOUGHT);
    }

    @Test(dataProvider = "6PairsOfPlayerName", expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSpecifiedPlayerIsAttached(String name1, String name2) throws IllegalArgumentException {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when - then
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        signHolder.attachPlayer(player1, Sign.CROSS);
    }


    @Test(dataProvider = "6PairsOfPlayerName")
    public void shouldReturnListOfAttachedPlayers(String name1, String name2) {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        signHolder.attachPlayer(player2, Sign.CROSS);
        // then
        Assert.assertEquals(signHolder.getAttachedPlayers(), new LinkedList<>(Arrays.asList(player1, player2)));

    }

    @Test(dataProvider = "6PairsOfPlayerName")
    public void shouldReturnListOfUsedSigns(String name1, String name2) {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        signHolder.attachPlayer(player2, Sign.CROSS);
        // then
        Assert.assertEquals(signHolder.getUsedSigns(), new LinkedList<>(Arrays.asList(Sign.NOUGHT, Sign.CROSS)));
    }

    @Test(dataProvider = "6PairsOfPlayerName")
    public void shouldReturnSignByPlayer(String name1, String name2) {
        // given
        when(player1.getName()).thenReturn(name1);
        when(player2.getName()).thenReturn(name2);
        SignHolder signHolder = new SignHolder();
        // when
        signHolder.attachPlayer(player1, Sign.NOUGHT);
        // then
        Assert.assertEquals(signHolder.getBookedSign(player1), Optional.of(Sign.NOUGHT));
        Assert.assertEquals(signHolder.getBookedSign(player2), Optional.empty());
    }
}
