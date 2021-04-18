package com.example.cs4227_project.Factory;

public class GenreFactory {

    public Genre getGenre(String genreType)
    {
        if(genreType.equals("action='Action'"))
        {
            return new ActionFactory();
        }
        if(genreType.equals("comedy='Comedy'"))
        {
            return new ComedyFactory();
        }
        if(genreType.equals("disney='Disney'}"))
        {
            return new DisneyFactory();
        }
        if(genreType.equals("horror='Horror'"))
        {
            return new HorrorFactory();
        }
        if(genreType.equals("romance='Romance'"))
        {
            return new RomanceFactory();
        }
        if(genreType.equals("scifi='Scifi'"))
        {
            return new SciFiFactory();
        }
        return null;
    }
}
