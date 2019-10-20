package com.seyrancom.consulting.test.unit;

import com.seyrancom.consulting.test.core.AbstractTestRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class LambdaTest extends AbstractTestRunner {

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testExample() {
        byte b = 127;
        logger.debug(String.valueOf(b));
        b++;
        logger.debug(String.valueOf(b));

        int a = 1;
        a = (int) 2.12;
        logger.debug(String.valueOf(a));

        assertTrue(b == -128);

        Runnable noArg = () -> logger.debug("HW!");

        List<Artist> allArtists = Arrays.asList(new Artist("London"), new Artist("Paris"), new Artist("Madrid"), new Artist("London"), new Artist("Kiev"));
        long count = 0;
        Iterator<Artist> iterator = allArtists.iterator();
        while (iterator.hasNext()) {
            Artist artist = iterator.next();
            if (artist.isFrom("London")) {
                count++;
            }
        }

        logger.debug(String.valueOf(count));

        count = allArtists.stream()
                .filter(artist -> artist.isFrom("London"))
                .count();

        logger.debug(String.valueOf(count));


        List<String> collected = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), collected);

        collected = new ArrayList<>();
        for (String string : Arrays.asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);

        collected = Stream.of("a", "b", "hello")
                .map(string -> {
                    logger.debug(string.toUpperCase());
                    return string.toUpperCase();
                })
                .collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), together);


        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));
        Track shortestTrack = tracks.stream()
                .min(Comparator.comparing(track -> track.getLength()))
                .get();
        assertEquals(tracks.get(1), shortestTrack);

        count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        assertEquals(6, count);

        /*Set<String> origins = album.getMusicians()
                .filter(artist -> artist.getName().startsWith("The"))
                .map(artist -> artist.getNationality())
                .collect(toSet());*/

        List<Album> la = Arrays.asList(new Album(), new Album(), new Album(), new Album(), new Album());
        Artist a1 = new Artist("London");
        Artist a2 = new Artist("Paris");
        Artist a3 = new Artist("Kiev");
        la.get(0).setMainMusician(a3);
        la.get(1).setMainMusician(a1);
        la.get(2).setMainMusician(a3);
        la.get(3).setMainMusician(a3);
        la.get(4).setMainMusician(a2);

        logger.debug("findLongTracksV2={}", findLongTracksV2(la));

        printTrackLengthStatistics(new Album());

        logger.debug("bandsAndSoloRef={}", bandsAndSoloRef(allArtists.stream()));
        logger.debug("albumsByArtist={}", albumsByArtist(la.stream()));

        String result =
                allArtists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.joining(", ", "[", "]"));
        logger.debug(result);
    }

    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>();
        albums.stream()
                .forEach(album -> {
                    album.getTracks()
                            .forEach(track -> {
                                if (track.getLength() > 60) {
                                    String name = track.getName();
                                    trackNames.add(name);
                                }
                            });
                });
        return trackNames;
    }

    public Set<String> findLongTracksV2(List<Album> albums) {
        return albums.stream()
                .flatMap(album -> album.getTracks().stream())
                .filter(track -> track.getLength() > 60)
                .map(track -> track.getName())
                .collect(Collectors.toSet());
    }

    public static void printTrackLengthStatistics(Album album) {
        IntSummaryStatistics trackLengthStats
                = album.getTracks().stream()
                .mapToInt(track -> track.getLength())
                .summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }

    public Map<Boolean, List<Artist>> bandsAndSoloRef(Stream<Artist> artists) {
        return artists.collect(Collectors.partitioningBy(artist -> artist.isSolo()));
    }

    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(Collectors.groupingBy(album -> album.getMainMusician()));
    }

    class Track {
        int length;
        String name;

        public Track(String name, int length) {
            this.name = name;
            this.length = length;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLength() {
            return length;
        }
    }

    class Album {

        private List<Track> _tracks = Arrays.asList(new Track("Bakai", 524),
                new Track("Violets for Your Furs", 378),
                new Track("Time Was", 451));

        private Artist mainMusician;

        public List<Track> getTracks() {

            return _tracks;
        }

        public Artist getMainMusician() {
            return mainMusician;
        }

        public void setMainMusician(Artist mainMusician) {
            this.mainMusician = mainMusician;
        }
    }

    class Artist {
        String city;

        public Artist(String city) {
            this.city = city;
        }

        Boolean isFrom(String param) {
            return city.equals(param);
        }

        public boolean isSolo() {
            return false;
        }

        public String getName() {
            return city;
        }
    }
}
