import 'package:dio/dio.dart';

import '../dio_instance.dart';

Future<Map<String, dynamic>> dioApiDeleteFriendOne(
    String accessToken, int friendId) async {
  Dio dio = DioInstance(accessToken: accessToken).dio;

  try {
    Response response = await dio.delete('/friend/$friendId');
    return {
      'statusCode': response.statusCode,
    };
  } on DioError catch (error) {
    return {
      'statusCode': error.response!.statusCode,
    };
  } finally {
    dio.close();
  }
}
