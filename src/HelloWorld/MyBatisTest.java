package HelloWorld;

import HelloWorld.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author TJ
 * @create 2021-1020 19:39
 * 1.根据XML配置文件(全局配置文件),数据源和运行环境信息 mybatis-config.xml
 * 2.SQL映射文件：配置了SQL和SQL封装规则 EmployeeMapper.xml
 * 3.将SQL映射文件保注册在全局配置文件
 * 4.创建一个SqlSessionFactory对象，从SqlSessionFactory获取SqlSession实例，能直接执行已经映射的SQL语句
 * 5.使用SQL的唯一表示来告诉MyBatis执行那个SQL，SQL都是保存在SQL映射文件中
 *
 */
public class MyBatisTest {
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            Employee employee = openSession.selectOne("mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test01() throws IOException {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory1 = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession openSession1 = sqlSessionFactory1.openSession();

        //获取接口的实现类对象
        //MyBatis会为接口自动的创建一个代理对象，代理对象去执行增删改查
        EmployeeMapper mapper = openSession1.getMapper(EmployeeMapper.class);

        Employee employee = mapper.getEmpId(1);

        System.out.println(mapper.getClass());

        openSession1.close();


    }
    @Test
    public void test2(){
        int[][] ans = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(ans[1][2]);
    }
}
