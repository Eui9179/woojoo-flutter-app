import 'package:dio/dio.dart';
import 'package:woojoo/data/memory/authentication/dto_access_token_response.dart';
import 'package:woojoo/data/memory/authentication/dto_fcm_request.dart';
import 'package:woojoo/data/memory/authentication/dto_login_request.dart';
import 'package:woojoo/data/memory/authentication/dto_signup_request.dart';
import 'package:woojoo/data/remote/authentication/authentication_repository.dart';

import '../dio_instance.dart';

class AuthenticationApi implements AuthenticationRepository {
  Dio dio = DioInstance().dio;

  AuthenticationApi._();

  static AuthenticationApi instance = AuthenticationApi._();

  @override
  Future<AccessTokenResponse> login(LoginRequest request) async {
    Response response = await dio.post(
      '/auth/login',
      data: request.toJson(),
    );
    return AccessTokenResponse.fromJson(response);
  }

  @override
  Future<AccessTokenResponse> signup(SignupRequest request) async {
    Response response = await dio.post(
      '/auth/signup',
      data: request.toForm(),
    );
    return AccessTokenResponse.fromJson(response);
  }

  @override
  Future<int> syncFcm(FcmRequest request) async {
    Response response = await dio.post(
      '/auth/async-token',
      data: request.toJson(),
    );
    return response.statusCode ?? 500;
  }

  @override
  Future<int> withdrawal(String accessToken) async {
    setDioHeaderAccessToken(accessToken);
    Response response = await dio.delete('/auth/withdrawal');
    return response.statusCode ?? 500;
  }

  void setDioHeaderAccessToken(String accessToken) {
    dio.options.headers["authorization"] = "Bearer $accessToken";
    dio.options.headers["content-Type"] = 'application/json';
  }
}