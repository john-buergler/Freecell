import java.io.IOException;

/**
 * Appendable that fails on purpose to test that an error gets thrown.
 */
public class FailingAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Fail!");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Fail!");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Fail!");
  }
}