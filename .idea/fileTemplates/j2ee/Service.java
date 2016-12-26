package ${PACKAGE_NAME};
#parse("File Header.java")

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

#parse("Class Header.java")
public class ${NAME} extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
