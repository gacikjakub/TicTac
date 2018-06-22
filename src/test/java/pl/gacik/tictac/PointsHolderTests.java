package pl.gacik.tictac;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import static org.mockito.Mockito.when;

public class PointsHolderTests {

//    @Spy
//    private Points points1 = Mockito.spy(new Points());

//    @Mock
//    private Points points2 = Mockito.mock(Points.class);
//
//    @Mock
//    private Points points3 = Mockito.mock(Points.class);
//
//    @Mock
//    private Points points4 = Mockito.mock(Points.class);

    @Mock
    private Player player1 = Mockito.mock(Player.class);

    @Mock
    private Player player2 = Mockito.mock(Player.class);

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "6PairsOfPlayerName")
    private Object[][] PairsOfPlayerName() {
        return new Object[][]{{"Zack", "Mark"}, {"Alex", "Ash"}, {"Jane", "Marry"}, {"Kenny", "Michael"}, {"Jack", "Fuck"},
                {"Andrew", "Joanna"}};
    }


    @Test(dataProvider = "6PairsOfPlayerName")
    public void shouldProperlyAddPlayer(String s1, String s2) {
        // given
        when(player1.getName()).thenReturn(s1);
        when(player2.getName()).thenReturn(s2);
        PointsHolder pointsHolder = new PointsHolder();
        // when
        pointsHolder.addPlayer(player1);
        pointsHolder.addPlayer(player2);
        // then
        Assert.assertEquals(pointsHolder.getAddedPlayers(), new HashSet<>(Arrays.asList(player1, player2)));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPlayerIsNull() {
        new PointsHolder().addPlayer(null);
    }


    @Test(dataProvider = "6PairsOfPlayerName", expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenPlayerIsAlreadyAdded(String s1, String s2) {
        // given
        when(player1.getName()).thenReturn(s1);
        PointsHolder pointsHolder = new PointsHolder();
        // when - then
        pointsHolder.addPlayer(player1);
        pointsHolder.addPlayer(player1);
    }

    @DataProvider(name = "10x4RandomIntegers")
    public Object[][] pointsPair() {
        Object[][] result = new Object[10][4];
        Random generator = new Random();
        for (int i = 0; i < 10; i++) {
            result[i][0] = generator.nextInt();
            result[i][1] = generator.nextInt();
            result[i][2] = generator.nextInt();
            result[i][3] = generator.nextInt();
        }
        return result;
    }


    @Test(dataProvider = "10x4RandomIntegers")
    public void shouldProperlyAddPointsToPlayers(Integer p1, Integer p2, Integer p3, Integer p4) {
        // given
        Points points1 = Mockito.spy(new Points(p1));
        Points points2 = Mockito.spy(new Points(p2));
        Points points3 = Mockito.spy(new Points(p3));
        Points points4 = Mockito.spy(new Points(p4));
        PointsHolder pointsHolder = new PointsHolder();
        pointsHolder.addPlayer(player1);
        pointsHolder.addPlayer(player2);
        // when
        pointsHolder.addPoints(player1, points1);
        pointsHolder.addPoints(player1, points2);
        pointsHolder.addPoints(player2, points3);
        pointsHolder.addPoints(player2, points4);
        int player1points = pointsHolder.getPlayerPoints(player1).stream().mapToInt(i -> i.points).sum();
        int player2points = pointsHolder.getPlayerPoints(player2).stream().mapToInt(i -> i.points).sum();
        // then
        Assert.assertEquals(player1points, p1 + p2);
        Assert.assertEquals(player2points, p3 + p4);
    }

    @Test(dataProvider = "10x4RandomIntegers")
    public void shouldReturnPlayerWithTheMostPointsAmount(Integer p1, Integer p2, Integer p3, Integer p4) {
        // given
        Points points1 = Mockito.spy(new Points(p1));
        Points points2 = Mockito.spy(new Points(p2));
        Points points3 = Mockito.spy(new Points(p3));
        Points points4 = Mockito.spy(new Points(p4));
        PointsHolder pointsHolder = new PointsHolder();
        pointsHolder.addPlayer(player1);
        pointsHolder.addPlayer(player2);
        // when
        pointsHolder.addPoints(player1, points1);
        pointsHolder.addPoints(player1, points2);
        pointsHolder.addPoints(player2, points3);
        pointsHolder.addPoints(player2, points4);
        int player1points = pointsHolder.getPlayerPoints(player1).stream().mapToInt(i -> i.points).sum();
        int player2points = pointsHolder.getPlayerPoints(player2).stream().mapToInt(i -> i.points).sum();
        Player winner = p1+p2 > p3+p4 ? player1 : p1+p2 < p3+p4 ? player2 : null;
        // then
        Assert.assertEquals(winner, pointsHolder.getWinner().get());
    }

}
