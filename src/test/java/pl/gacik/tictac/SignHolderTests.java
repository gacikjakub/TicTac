package pl.gacik.tictac;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

}
