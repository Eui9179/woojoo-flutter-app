import 'package:flutter/material.dart';

class FontButton extends StatelessWidget {
  const FontButton(
      {Key? key,
      required this.onPressed,
      required this.text,
      required this.color})
      : super(key: key);

  final VoidCallback onPressed;
  final String text;
  final Color color;

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: onPressed,
      child: Text(text, style: TextStyle(color: color, fontSize: 17)),
    );
  }
}
