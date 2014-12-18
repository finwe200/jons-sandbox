package jon.sandbox.eclipse.ui.model.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jon.sandbox.eclipse.ui.model.person.Gender;

public class ModelProvider
{
  public static final int NUM_AUTHORS = 1000;

  private ModelProvider()
  {
    m_rand = new Random(System.currentTimeMillis());
    m_authors = new ArrayList<Author>(NUM_AUTHORS);

    for (int i = 0; i < NUM_AUTHORS; i++)
    {
      Author author = new Author(generateFirstName(), generateLastName(), Gender.eMale, true, (i % 50) +18);
      m_authors.add(author);

      int numBooks = (Math.abs(m_rand.nextInt()) % 4) + 1;
      for (int i1 = 0; i1 < numBooks; i1++)
      {
        author.addBook(new Book(author, "Book #" + (i1 + 1) + " (" + author.getLastName() + ")"));
      }
    }
  }

  public static ModelProvider getInstance()
  {
    if (m_instance == null) {
      m_instance = new ModelProvider();
    }
    return m_instance;
  }

  public List<Author> getAuthors()
  {
    return m_authors;
  }

  private String generateFirstName()
  {
    int index = Math.abs(m_rand.nextInt()) % ms_firstNames.length;
    return ms_firstNames[index];
  }

  private String generateLastName()
  {
    int index = Math.abs(m_rand.nextInt()) % ms_lastNames.length;
    return ms_lastNames[index];
  }

  private static final String[] ms_firstNames =
  {
    "Jon", "Andy", "Bob", "Fred", "Cindy",
    "Alicia", "Jody", "Robin", "Justin", "Mark"
  };

  private static final String[] ms_lastNames =
  {
    "Smith", "Sorensen", "Hubbel", "Tolkien", "Baggins",
    "Chapin", "Werziner", "Nix", "Hopkins", "Davis"
  };

  private static ModelProvider m_instance = null;

  private final List<Author> m_authors;
  private final Random m_rand;
}
