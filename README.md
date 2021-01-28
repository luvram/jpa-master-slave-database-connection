# JPA 에서 DB 커넥션 두개 연결하기
## 개론
데이터 백업 및 데이터베이스 접근 속도 향상을 위해 데이터베이스를 Master-Slave 구조로 지정하는 방법이 있다. 이때 Slave는 읽기 전용으로 사용하고 Master는 쓰기 전용으로 사용한다. 이 둘 사이에 Replication을 걸어 Master에서 변경된 데이터가 주기적으로 Slave로 복사되도록 설정하여 사용한다.

따라서 데이터를 읽을때는 Slave 에, 데이터를 쓸때 (Create/Update/Delete) 는 Master에 연결하여 작업하게 된다. 때문에 JPA에서 데이터베이스 커넥션을 두개를 유지해야 하는 상황이 생기는데, 이를 위한 샘플을 만들어본다.

## 설명
1. 데이터베이스를 여러개 연결할때는 `DataSource`를 연결할 데이터베이스마다 설정해주어야 한다. 여기서는 Master와 Slave용 `DataSource`를 정의해줄것이다. (`DataSourceConfig` 클래스 참고)
2. 생성된 `DataSource`는 데이터소스를 동적으로 선택할 수 있게끔 `routingDataSource`로 등록을 한다. (`DataSourceConfig` 클래스 참고)
3. 트랜잭션의 상태가 `read-only`이면 Slave DB에 접근하고 그 밖에는 Master DB에 접근하도록 하여야 한다. `AbstractRoutingDatasource` 클래스를 이용하여 트랜잭션의 읽기 상태에 따라 Master 혹은 Slave를 리턴하도록 작성해준다. (`ReplicationRoutingDataSource` 클래스 참고)
4. `@Transactional` 어노테이션에 `readOnly=true` 가 설정되면 자동으로 Slave DB 에 접근하게 된다. (`UserService` 클래스 참고) 

  