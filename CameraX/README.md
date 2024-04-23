# Camera X 예제

## Basic Concept
 - Camera X는 기기 호환성/유저 편의성/일관성을 만족하는 Jetpack 라이브러리
 - Camera X는 크게 2가지 방법으로 작동 가능(CameraController, CameraProvider)
 - 2가지 방법을 모두 적용시킬 경우 CameraController 대신 CameraProvider가 작동되는 것으로 보임(추가 확인 필요)
 - 두 방법 모두 4가지 용례(Usecase)를 지원: Preview, ImageCapture, VideoCapture, ImageAnalysis

## CameraController
 - 줌, 초점 등 기능을 자동으로 처리
 - CameraProvider에 비해 간단하게 사용 가능
 - 다만 복잡한 Usecase는 구현 불가

## CameraProider
 - CameraController에 비해 비교적 복잡하지만 더 많은 요소를 처리할 수 있음: 화면 비율, 사진 비율