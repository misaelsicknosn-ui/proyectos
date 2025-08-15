package mx.reinamadre.citas.configuracion;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class SQLiteCustomDialect extends Dialect {
    public SQLiteCustomDialect() {
        registerFunction("concat", new StandardSQLFunction("concat", StringType.INSTANCE));
    }
}