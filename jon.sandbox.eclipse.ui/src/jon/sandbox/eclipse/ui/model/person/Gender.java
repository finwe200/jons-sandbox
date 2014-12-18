package jon.sandbox.eclipse.ui.model.person;

public enum Gender
{
  eMale
  {
    @Override
    public String toString()
    {
      return "Male";
    }
  },
  eFemale
  {
    @Override
    public String toString()
    {
      return "Female";
    }
  },
  eAndrogynous
  {
    @Override
    public String toString()
    {
      return "Androgynous";
    }

    @Override
    public boolean canMarry()
    {
      return false;
    }
  };

  @Override
  abstract public String toString();

  public boolean canMarry()
  {
    return true;
  }
}
