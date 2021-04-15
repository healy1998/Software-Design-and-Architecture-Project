package com.example.cs4227_project;

public class GenreFactory {

    public Genre getGenre(String genreType)
    {
        if(genreType == "Action")
        {
            return new ActionFactory();
        }
        if(genreType == "Comedy")
        {
            return new ComedyFactory();
        }
        if(genreType == "Disney")
        {
            return new DisneyFactory();
        }
        if(genreType == "Horror")
        {
            return new HorrorFactory();
        }
        if(genreType == "Romance")
        {
            return new RomanceFactory();
        }
        if(genreType == "Sci-fi")
        {
            return new SciFiFactory();
        }
        return null;
    }
}
