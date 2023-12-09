package Java.DataBase.Querys;

public interface SQLQuery {
	abstract void execute(String query);

	abstract String getResult();
}
