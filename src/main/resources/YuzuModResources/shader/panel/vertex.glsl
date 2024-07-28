attribute vec4 a_position;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec2 v_texCoord;

void main() {
    // 调整顶点位置以形成半圆曲线
    vec4 pos = a_position;
    float angle = pos.x * 3.14159265; // 根据X坐标计算角度
    pos.x = cos(angle) * pos.y; // 调整X坐标
    pos.y = sin(angle) * pos.y; // 调整Y坐标

    v_texCoord = a_texCoord0;
    gl_Position = u_projTrans * pos;
}
