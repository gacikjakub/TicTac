package pl.gacik.tictac;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class TurnHolderTests {

    @Mock
    private Player player1 = Mockito.mock(Player.class);

    @Mock
    private Player player2 = Mockito.mock(Player.class);

    @Mock
    private Player player3 = Mockito.mock(Player.class);

    @Test
    public void shouldGetNextPlayerReturnAddedPlayerAlternately() {
        // given
        String[] names = new String[]{"Franc", "Jane", "Alex"};
        when(player1.getName()).thenReturn(names[0]);
        when(player2.getName()).thenReturn(names[1]);
        when(player3.getName()).thenReturn(names[2]);
        TurnHolder turnHolder = new TurnHolder(player1, player2, player3);
        // when - then
        for (int i = 0; i < 200; i++) {
            if (!turnHolder.getNextPlayer().getName().equals(names[i % names.length])) {
                Assert.fail();
            }
        }
    }

    @Test
    public void shouldReturnProperAmountOfPlayers() {
        // given
        String[] names = new String[]{"Franc", "Jane", "Alex"};
        when(player1.getName()).thenReturn(names[0]);
        when(player2.getName()).thenReturn(names[1]);
        when(player3.getName()).thenReturn(names[2]);
        TurnHolder turnHolder = new TurnHolder(player1, player2, player3);
        // when - then
        Assert.assertEquals(3, turnHolder.getPlayersAmount());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenOneOfPlayerIsNull() {
        // given
        String[] names = new String[]{"Franc", "Jane", "Alex"};
        when(player1.getName()).thenReturn(names[0]);
        when(player2.getName()).thenReturn(names[1]);
        // when - then
        new TurnHolder(player1, player2, null);
    }
}