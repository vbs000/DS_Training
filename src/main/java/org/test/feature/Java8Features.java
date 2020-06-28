package org.test.feature;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Features {
    //1.Lambda exp
    //2.Function ref
    //3.Stream API

    public static void main(String[] args) {

        //1.Lambda

        //匿名内部类写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("New thread test.");
            }
        }).start();
        //Lambda写法
        new Thread(()->{
            System.out.println("New thread test.(invoke dynamic)");
        }).start();;

        //2.Function ref
        //直接用Function类型来传递函数
        Function<String, Integer> s = Integer::parseInt;
        Integer i = s.apply("10");
        System.out.println("parseInt:"+i);
        //用实现了@FunctionalInterface函数式接口的方法来传递函数
        Comparator<Integer> comparator = Integer::compare;
        int result = comparator.compare(100,10);
        System.out.println("compare result:"+result);
        //用实现了@FunctionalInterface函数式接口的方法来传递函数2
        IntBinaryOperator intBinaryOperator = Integer::compare;
        int result2 = intBinaryOperator.applyAsInt(10,100);
        System.out.println("compare result2:"+result2);

        //自定义用@FunctionalInterface注解的函数式接口
        //调用类的方式实现
        KiteFunction<LocalDateTime,String,String> functionDateFormat = FunctionTest::DateFormat;
        String dateString = functionDateFormat.run(LocalDateTime.now(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString);

        //匿名类的方式实现
        String dateString2 = new KiteFunction<LocalDateTime, String, String>() {
            @Override
            public String run(LocalDateTime localDateTime, String s) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(s);
                return localDateTime.format(dateTimeFormatter);
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString2);
        //Lambda的方式实现
        KiteFunction<LocalDateTime, String, String> functionDateFormat2 = (LocalDateTime dateTime, String partten) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(partten);
            return dateTime.format(dateTimeFormatter);
        };
        String dateString3 = functionDateFormat.run(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateString3);

        //Stream API
        //of
        Stream<String> a = Stream.of("a","b","c");
        //empty
        Stream b = Stream.empty();
        //concat
        Stream<String> c = Stream.concat(a,b);
        //max or min
        Stream<Integer> integerStream = Stream.of(2, 2, 100, 5);
        Integer max = integerStream.max(Integer::compareTo).get();
        System.out.println(max);

        Stream<Integer> integerStream2 = Stream.of(2, 2, 100, 5);
        Comparator<Integer> comparator2 =  (x, y) -> (x.intValue() < y.intValue()) ? -1 : ((x.equals(y)) ? 0 : 1);
        Integer min = integerStream2.min(comparator2).get();
        System.out.println(min);

        //count
        Stream<String> d = Stream.of("a", "b", "c");
        long d_count = d.count();
        System.out.println("d_count:"+d_count);
        //peek
        Stream<String> e = Stream.of("a", "b", "c");
        //通道，消费者函数式接口
        List<String> list = e.peek(f->System.out.println(f.toUpperCase())).collect(Collectors.toList());
        //forEach，消费者函数式接口
        Stream<String> g = Stream.of("a", "b", "c");
        g.forEach(h->System.out.println(h.toUpperCase()));
        //forEachOrdered，并行时保证顺序
        Stream<String> k = Stream.of("a", "b", "c");
        k.parallel().forEachOrdered(j->System.out.println(j.toUpperCase()));

        //limit，获取前N条数据
        Stream<String> l = Stream.of("a", "b", "c");
        l.limit(2).forEach(m->System.out.println(m));

        //skip，跳过前N条数据
        Stream<String> n = Stream.of("a", "b", "c");
        n.skip(2).forEach(m->System.out.println(m));
        //distinct，去重复
        Stream<String> o = Stream.of("a", "b", "c","b");
        o.distinct().forEach(m->System.out.println(m));

        //自然顺序排序
        Stream<String> p = Stream.of("a", "c", "b");
        p.sorted().forEach(m->System.out.println(m));
        //自定义顺序排序
        Stream<String> q = Stream.of("a1", "c6", "b3");
        q.sorted((x,y)->Integer.parseInt(x.substring(1))>Integer.parseInt(y.substring(1))?1:-1).forEach(m->System.out.println(m));

        //选出性别为 0，年龄大于 50 的记录
        List<User> users = getUserData();
        Stream<User> stream = users.stream();
        stream.filter(user -> user.getGender().equals(0) && user.getAge()>50).forEach(m->System.out.println(m.getUserName()+","+m.getGender()+","+m.getAge()));

//        /**
//         *等同于下面这种形式 匿名内部类
//         */
//        Stream<User> stream2 = users.stream();
//        stream2.filter(new Predicate<User>() {
//            @Override
//            public boolean test(User user) {
//                return user.getGender().equals(0) && user.getAge()>50;
//            }
//        }).forEach(m->System.out.println(m));
        //map映射，转换数据类型

//        List<User> users = getUserData();
//        Stream<User> stream = users.stream();
//        List<UserDto> userDtos = stream.map(user -> dao2Dto(user)).collect(Collectors.toList());
        //collect收集器
        Stream<Integer> integerStreamx = Stream.of(1,2,5,7,8,12,33);
        List<Integer> listx= integerStreamx.filter(m -> m.intValue()>7).collect(Collectors.toList());

        Stream<Integer> integerStreamy = Stream.of(1,2,5,7,8,12,33);
        List<Integer> listy = integerStreamy.filter(m -> m.intValue()>7).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
        List<User> userx = getUserData();

        //groupingBy
        // 返回 userId:List<User>
        Map<Integer,List<User>> map = userx.stream().collect(Collectors.groupingBy(User::getUserId));

        //返回 userId:每组个数
        Map<Integer,Long> map2 = userx.stream().collect(Collectors.groupingBy(User::getUserId,Collectors.counting()));

        //toArray
        Stream<User> stream3 = userx.stream();
        User[] userArray = stream3.filter(user -> user.getGender().equals(0) && user.getAge() > 50).toArray(User[]::new);

        //reduce
        //每次计算的时候都用到上一次的计算结果
        Stream<Integer> integerStream_t = Stream.of(1,2,5,7,8,12,33);
        Integer sum = integerStream_t.reduce(0,(x,y)->x+y);
        System.out.println(sum);
    }

//    private static UserDto dao2Dto(User user){
//        UserDto dto = new UserDto();
//        BeanUtils.copyProperties(user, dto);
//        //其他额外处理
//        return dto;
//    }

    private static List<User> getUserData() {
        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName(String.format("古时的风筝 %s 号", i));
            user.setAge(random.nextInt(100));
            user.setGender(i % 2);
            user.setPhone("18812021111");
            user.setAddress("无");
            users.add(user);
        }
        return users;
    }
    private static class User{
        private int userId;
        private String userName;
        private int age;
        private Integer gender;
        private String phone;
        private String address;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    @FunctionalInterface
    public interface KiteFunction<T, R, S> {

        /**
         * 定义一个双参数的方法
         * @param t
         * @param s
         * @return
         */
        R run(T t,S s);
    }

    public static class FunctionTest {
        public static String DateFormat(LocalDateTime dateTime, String partten) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(partten);
            return dateTime.format(dateTimeFormatter);
        }
    }


}
